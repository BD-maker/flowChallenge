package com.example.flowchallengue.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.flowchallengue.core.CharactersDetailViewModelFactory
import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.core.domain.model.ViewState
import com.example.flowchallengue.core.domain.usecases.GetCharacterDetailUseCase
import com.example.flowchallengue.databinding.FragmentCharactersListBinding
import com.example.flowchallengue.ui.viewmodel.CharacterDetailViewModel

class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterDetailViewModel by viewModels {
        CharactersDetailViewModelFactory(GetCharacterDetailUseCase.create())
    }
    private val args: CharacterDetailFragmentArgs by navArgs()
    private var characterId : String? = null

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
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterId = args.characterId
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        characterId?.let{viewModel.getCharacter(it)}
    }

    private fun showLoading(){

    }

    private fun showScreen( model : CharacterModel){

    }

    private fun showErrorScreen(error : Throwable){

    }




}