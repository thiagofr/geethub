package com.thiagofr.geethub.presenter.userlist

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
import com.thiagofr.geethub.databinding.FragmentUserListBinding
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.presenter.userlist.adapter.UsersAdapter
import com.thiagofr.geethub.util.LOGIN_EXTRA
import com.thiagofr.geethub.util.gone
import com.thiagofr.geethub.util.setDividerItemDecoration
import com.thiagofr.geethub.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.thiagofr.geethub.presenter.userlist.UserListViewAction as ViewAction

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val userListViewModel: UserListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        setupToolbar()
        return binding.root
    }

    private fun setupToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userListViewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is UserListViewState.Loading -> setLoading()
                is UserListViewState.SetUserList -> setUserList(it.userList)
                UserListViewState.Error -> setError()
            }
        }
    }

    private fun setUserList(userList: List<User>?) {
        userList?.let {
            with(binding.rvUserList) {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                adapter = UsersAdapter(userList) { user ->

                    val bundle = Bundle()
                    bundle.putString(LOGIN_EXTRA, user.login)
                    findNavController(binding.root).navigate(
                        R.id.action_userListFragment_to_userFragment,
                        bundle
                    )
                }
                visible()
                setDividerItemDecoration()
            }
            binding.loading.gone()
        }
    }

    private fun setError() {
        binding.loading.gone()
        binding.rvUserList.gone()
        binding.error.visible()
    }

    private fun setLoading() {
        binding.loading.visible()
        binding.rvUserList.gone()
        binding.error.gone()
    }

    override fun onResume() {
        super.onResume()
        userListViewModel.dispatchAction(ViewAction.GetUserList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
