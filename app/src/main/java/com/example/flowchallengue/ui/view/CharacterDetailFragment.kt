package com.example.flowchallengue.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flowchallengue.R
import com.example.flowchallengue.core.CharactersDetailViewModelFactory
import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.core.domain.model.ViewState
import com.example.flowchallengue.core.domain.usecases.GetCharacterDetailUseCase
import com.example.flowchallengue.databinding.FragmentCharacterBinding
import com.example.flowchallengue.ui.viewmodel.CharacterDetailViewModel
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.net.UnknownHostException

class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterDetailViewModel by viewModels {
        CharactersDetailViewModelFactory(GetCharacterDetailUseCase.create())
    }
    private val args: CharacterDetailFragmentArgs by navArgs()
    private var characterId: String? = null

    private val viewStateObserver = Observer<ViewState<Any>> { viewState ->
        when (viewState) {
            is ViewState.Loading -> showLoading()
            is ViewState.Success -> showScreen(viewState.model as CharacterModel)
            is ViewState.Error -> showErrorScreen(viewState.error)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterId = args.characterId
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        characterId?.let { viewModel.getCharacter(it) }
    }

    private fun showLoading() {
        binding.apply {
            loadingLottie.visibility = View.VISIBLE
            tvLoading.visibility = View.VISIBLE
        }

    }

    private fun showScreen(model: CharacterModel) {
        binding.apply {
            loadingLottie.visibility = View.GONE
            tvLoading.visibility = View.GONE
            Picasso.get().load(model.image).into(ivCharacter)
            tvCharacterName.text = "Name: ${model.name}"
            tvCharacterOrigin.text = "Origin: ${model.origin?.name}"
            tvCharacterSpecie.text = "Specie: ${model.species}"
            tvCharacterStatus.text = "Status: ${model.status}"
            backArrowContainer.setOnClickListener {
                if (findNavController().currentDestination?.id == R.id.characterFragment) {
                    findNavController().navigate(
                        CharacterDetailFragmentDirections.actionDetailToList()
                    )
                }
            }
            motionDetail.transitionToState(R.id.detailEnd)
        }
    }

    private fun showErrorScreen(error: Throwable) {
        when (error) {
            is UnknownHostException -> Toast.makeText(
                context,
                "No hay conexión a internet", Toast.LENGTH_SHORT
            ).show()
            is Exception -> Toast.makeText(
                context,
                "Ha ocurrido un problema, intente más tarde", Toast.LENGTH_SHORT
            ).show()
        }
    }
}
