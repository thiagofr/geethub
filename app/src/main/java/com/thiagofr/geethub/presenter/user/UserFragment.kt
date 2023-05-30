package com.thiagofr.geethub.presenter.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.thiagofr.geethub.R
import com.thiagofr.geethub.databinding.FragmentUserBinding
import com.thiagofr.geethub.domain.model.Repository
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.presenter.user.adapter.RepositoryAdapter
import com.thiagofr.geethub.util.LOGIN_EXTRA
import com.thiagofr.geethub.util.gone
import com.thiagofr.geethub.util.setDividerItemDecoration
import com.thiagofr.geethub.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.thiagofr.geethub.presenter.user.UserViewAction as ViewAction


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        setupToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is UserViewState.Loading -> setLoading()
                is UserViewState.SetUserInfo -> setUserInfo(it.data)
                UserViewState.Error -> setError()
            }
        }

        val login = arguments?.getString(LOGIN_EXTRA).orEmpty()
        userViewModel.dispatchAction(ViewAction.Init(login))
    }

    private fun setUserInfo(user: User?) {
        with(binding) {
            user?.let {
                tvLogin.text = user.login
                tvName.text = user.name
                tvCity.text = user.location

                user.followers?.let {
                    setFollowersInfo(it)
                }

                user.following?.let {
                    setFollowingInfo(it)
                }
                setImageUser(user.avatarUrl)
                setRepositoryList(user.repositoryList)

                content.visible()
                loading.gone()
            }
        }
    }

    private fun setRepositoryList(repositoryList: List<Repository>?) {
        with(binding) {
            rvRepo.layoutManager = LinearLayoutManager(
                this@UserFragment.requireContext(),
                RecyclerView.VERTICAL,
                false
            )
            rvRepo.adapter = RepositoryAdapter(
                repositoryList ?: emptyList()
            )

            rvRepo.setDividerItemDecoration()
        }
    }

    private fun setFollowingInfo(followers: Int) {
        val followersText =
            resources.getQuantityString(
                R.plurals.following,
                followers,
                followers
            )
        binding.tvFollowing.text = followersText
    }

    private fun setFollowersInfo(followers: Int) {
        val followersText =
            resources.getQuantityString(
                R.plurals.followers,
                followers,
                followers
            )
        binding.tvFollowers.text = followersText
    }

    private fun setImageUser(avatarUrl: String) {
        Glide.with(this)
            .load(avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(com.thiagofr.geethub.R.drawable.ic_coragem)
            .into(binding.imgUser)
    }

    private fun setLoading() {
        binding.loading.visible()
        binding.content.gone()
        binding.error.gone()
    }

    private fun setError() {
        binding.loading.gone()
        binding.content.gone()
        binding.error.visible()
    }

    private fun setupToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}