package com.example.flowchallengue.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flowchallengue.R
import com.example.flowchallengue.core.CharactersListViewModelFactory
import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.core.domain.model.ViewState
import com.example.flowchallengue.core.domain.usecases.GetCharactersUseCase
import com.example.flowchallengue.databinding.FragmentCharactersListBinding
import com.example.flowchallengue.ui.adapter.CharacterAdapter
import com.example.flowchallengue.ui.viewmodel.CharactersListViewModel
import java.lang.Exception
import java.net.UnknownHostException

class CharactersListFragment : Fragment(), CharacterAdapter.OnItemClickListener {

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
        getCharacters()
    }

    private fun getCharacters(page: String = "1") {
        viewModel.getAllCharacters(page)
    }

    private fun initRecyclerView() {
        binding.rvCharacters.layoutManager = GridLayoutManager(activity, 3)
    }

    private fun showLoading() {
        binding.apply {
            loadingLottie.visibility = View.VISIBLE
            tvLoading.visibility = View.VISIBLE
            rvCharacters.visibility = View.GONE
        }

    }

    private fun showScreen(model: CharactersListData) {
        val adapter = CharacterAdapter(model.results, this)

        binding.apply {
            rvCharacters.adapter = adapter
            loadingLottie.visibility = View.GONE
            tvLoading.visibility = View.GONE
            rvCharacters.visibility = View.VISIBLE
            model.info.prev?.let { address ->
                backArrow.visibility = View.VISIBLE
                backArrow.setOnClickListener {
                    getCharacters(address.filter { it.isDigit() })
                }
            } ?: run { backArrow.visibility = View.INVISIBLE }
            model.info.next?.let { address ->
                nextArrow.visibility = View.VISIBLE
                nextArrow.setOnClickListener {
                    getCharacters(address.filter { it.isDigit() })
                }
            } ?: run { nextArrow.visibility = View.INVISIBLE }
        }
    }

    override fun onItemClick(id: String) {
        if (id.isNotEmpty() &&
            findNavController().currentDestination?.id == R.id.charactersListFragment
        ) {
            findNavController().navigate(CharactersListFragmentDirections.actionListToDetail(id))
        }
    }

    private fun showErrorScreen(error: Throwable) {
        when (error) {
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
