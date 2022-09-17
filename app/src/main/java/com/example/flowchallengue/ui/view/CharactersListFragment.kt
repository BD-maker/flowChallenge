package com.example.flowchallengue.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowchallengue.core.CharactersListViewModelFactory
import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.core.domain.model.ViewState
import com.example.flowchallengue.core.domain.usecases.GetCharactersUseCase
import com.example.flowchallengue.databinding.FragmentCharactersListBinding
import com.example.flowchallengue.ui.adapter.CharacterAdapter
import com.example.flowchallengue.ui.viewmodel.CharactersListViewModel
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

class CharactersListFragment : Fragment() {

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersListViewModel by viewModels {
        CharactersListViewModelFactory(GetCharactersUseCase.create())
    }

    private val viewStateObserver = Observer<ViewState<Any>> { viewState ->
        when (viewState) {
            is ViewState.Loading -> showLoading()
            is ViewState.Success -> showScreen(viewState.model as CharactersListData)
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
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        initRecyclerView()
        viewModel.getAllCharacters()
    }


    private fun initRecyclerView(){
        binding.rvCharacters.layoutManager = GridLayoutManager(activity, 3)
    }
    private fun showLoading() {
        binding.loadingSpinner.visibility = View.VISIBLE
    }

    private fun showScreen(model: CharactersListData) {
        val adapter = CharacterAdapter(model.results)
        binding.rvCharacters.adapter = adapter
    }

    private fun showErrorScreen(error : Throwable) {
        when(error){
            is UnknownHostException -> Toast.makeText(
                context,
                "No hay conexión a internet", LENGTH_SHORT
            ).show()
            is Exception -> Toast.makeText(
                context,
                "Ha ocurrido un problema, intente más tarde", LENGTH_SHORT
            ).show()
        }
    }
}

