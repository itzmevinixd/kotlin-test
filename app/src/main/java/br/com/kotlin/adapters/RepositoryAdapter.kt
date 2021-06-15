package br.com.kotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.kotlin.R
import br.com.kotlin.data.model.Repository
import br.com.kotlin.databinding.ItemViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class RepositoryAdapter :
    PagingDataAdapter<Repository, RepositoryAdapter.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repository) {

            binding.apply {
                Glide.with(itemView)
                    .load(repo.owner.avatarUrl)
                    .centerCrop()
                    .error(android.R.drawable.stat_notify_error)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(avatar)

                Glide.with(itemView)
                    .load(R.drawable.ic_baseline_star)
                    .into(star)

                Glide.with(itemView)
                    .load(R.drawable.ic_fork)
                    .into(fork)

                login.text = repo.owner.login
                name.text = repo.name
                starCount.text = repo.stars.toString()
                forkCount.text = repo.forks.toString()
            }
        }
    }

}