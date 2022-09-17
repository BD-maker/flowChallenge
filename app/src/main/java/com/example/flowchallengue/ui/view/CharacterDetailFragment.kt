package com.example.flowchallengue.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flowchallengue.core.CharactersListViewModelFactory
import com.example.flowchallengue.core.domain.usecases.GetCharactersUseCase
import com.example.flowchallengue.ui.viewmodel.CharacterDetailViewModel

class CharacterDetailFragment : Fragment() {

    private val viewModel: CharacterDetailViewModel by viewModels {
        CharactersListViewModelFactory(GetCharactersUseCase.create())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}