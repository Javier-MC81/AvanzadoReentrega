package com.jmoreno.avanzado.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jmoreno.avanzado.R
import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: SuperheroViewModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var mMap: GoogleMap
    private lateinit var heroDetail: LocalSuperhero
    private lateinit var totalLocations: List<LatLng>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.findHero(args.superheroId)
        viewModel.getLocations(args.superheroId)

        viewModel.hero.observe(viewLifecycleOwner) { hero ->
            heroDetail = hero
            binding.tvName.text  = hero.name
            binding.tvDescription.text = hero.description
            Log.d("SUPERHERO","${heroDetail.name} tiene estado ${heroDetail.favorite}")

            if (hero.photo.isNotEmpty())
                Picasso.get().load(hero.photo)
                    .fit()
                    .centerInside()
                    .into(binding.ivPersonaje)

        }

        binding.bFavorito.setOnClickListener {
            heroDetail.favorite = !heroDetail.favorite
            changeFavorite(args.superheroId,heroDetail)
            Log.d("SUPERHERO","${heroDetail.name} tiene estado ${heroDetail.favorite}")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeFavorite(id: String,hero: LocalSuperhero){
        viewModel.changeFavorite(id,hero)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        viewModel.locations.observe(viewLifecycleOwner){ localizations->

            totalLocations = localizations.map { localization->
                LatLng(localization.latitud.toDouble(),localization.longitud.toDouble())
            }
            totalLocations.forEach {location->
                mMap.addMarker(
                    MarkerOptions()
                        .position(location)
                        .title("${heroDetail.name} ha estado aquí"))
        }
        }
    }
}
