package com.example.flowchallengue.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flowchallengue.ui.viewmodel.CharacterViewModel

class CharacterFragment : Fragment() {

    val viewModel : CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}