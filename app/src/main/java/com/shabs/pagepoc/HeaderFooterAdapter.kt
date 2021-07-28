package com.shabs.pagepoc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shabs.pagepoc.databinding.FooterViewItemBinding

class HeaderFooterAdapter(private val retry: () -> Unit) : LoadStateAdapter<HeaderFooterAdapter.CustomViewHolder>() {
    class CustomViewHolder(
        private val binding: FooterViewItemBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.textViewError.text = loadState.error.localizedMessage
            }
            binding.progressbar.isVisible = loadState is LoadState.Loading
            binding.buttonRetry.isVisible = loadState is LoadState.Error
            binding.textViewError.isVisible = loadState is LoadState.Error
            binding.buttonRetry.setOnClickListener {
                retry()
            }
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): CustomViewHolder {
        return CustomViewHolder(FooterViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), retry )
    }

}
