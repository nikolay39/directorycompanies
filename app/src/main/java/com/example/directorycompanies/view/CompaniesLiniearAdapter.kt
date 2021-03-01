/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.directorycompanies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.directorycompanies.R
import com.example.directorycompanies.databinding.CompanyListItemBinding
import com.example.directorycompanies.repository.network.entity.CompanyPreview
import com.squareup.picasso.Picasso
import java.util.*

class CompaniesLiniearAdapter(val clickListeter: CompanyListener) :
        ListAdapter<CompanyPreview, CompaniesLiniearAdapter.CompanyHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyHolder {
        val binding = CompanyListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CompanyHolder(binding)
    }

    override fun onBindViewHolder(holder: CompanyHolder, position: Int) {
        holder.bind(getItem(position), clickListeter)
    }

    inner class CompanyHolder(val binding: CompanyListItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(companyPreview: CompanyPreview, clickListener: CompanyListener) {
            binding.name.text = companyPreview.name
            binding.id.text = companyPreview.id.toString()
            Picasso.get()
                .load("https://lifehack.studio/test_task/"+companyPreview.img)
                .placeholder(R.drawable.ic_image_place_holder)
                .placeholder(R.drawable.ic_broken_image)
                .into(binding.img)
            binding.root.setOnClickListener { clickListeter }
        }
    }
}
class CompanyListener(val clickListeter: (companyId: Int) -> Unit) {
    fun onClick(companyPreview: CompanyPreview) = clickListeter(companyPreview.id)
}
class DiffCallback : DiffUtil.ItemCallback<CompanyPreview>() {
    override fun areItemsTheSame(oldItem: CompanyPreview, newItem: CompanyPreview):Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: CompanyPreview, newItem: CompanyPreview):Boolean  =
            (oldItem.id == newItem.id)
}
