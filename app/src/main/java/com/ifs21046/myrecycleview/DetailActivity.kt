package com.ifs21046.myrecycleview

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21046.myrecycleview.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var group: Group? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        group = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_GROUP, Group::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_GROUP)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (group != null) {
            supportActionBar?.title = "Kelompok ${group!!.name}"
            loadData(group!!)
        } else {
            finish()
        }
    }

    private fun loadData(group: Group) {
        binding.bookIconRiskianaomi.setImageResource(group.icon)
        binding.bookNameRiskianaomi.text = group.name
        binding.bookDescRiskianaomi.text = group.description
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_GROUP = "extra_group"
    }
}
