package com.jmoreno.avanzado.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jmoreno.avanzado.databinding.FragmentSuperheroListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class SuperheroListFragment : Fragment() {

    private var _binding: FragmentSuperheroListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: SuperheroViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSuperheroListBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SuperheroAdapter{superheroId->
            findNavController().navigate(SuperheroListFragmentDirections.actionSuperheroListFragmentToDetailFragment(superheroId))
        }

        binding.superheroList.adapter = adapter
        //Escuchamos cualquier cambio en la variable heros del viewmodel para enviar la lista al adaptador y la pinte
        viewModel.heros.observe(viewLifecycleOwner) { heros ->
            adapter.submitList(heros)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}