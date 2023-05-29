package com.thiagofr.geethub.presenter.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.geethub.R
import com.thiagofr.geethub.databinding.FragmentMainBinding
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.presenter.main.adapter.UsersAdapter
import com.thiagofr.geethub.util.gone
import com.thiagofr.geethub.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        setupToolbar()
        return binding.root
    }

    private fun setupToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is MainViewState.Loading -> setLoading(it.isLoading)
                is MainViewState.SetUserList -> setUserList(it.userList)
                MainViewState.Error -> setError()
            }
        }
    }

    private fun setUserList(userList: List<User>) {
        with(binding.rvUserList) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = UsersAdapter(userList) { user ->
                findNavController(binding.root).navigate(R.id.action_mainFragment_to_userFragment)
            }
            visible()
        }
        binding.loading.gone()
    }

    private fun setError() {
        binding.loading.gone()
        binding.rvUserList.gone()
        binding.error.visible()
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loading.visible()
            binding.rvUserList.gone()
            binding.error.gone()
        } else {
            binding.loading.gone()
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
