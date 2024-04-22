package com.ifs21046.myrecycleview

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21046.myrecycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataGroup = ArrayList<Group>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookgroup.setHasFixedSize(false)
        dataGroup.addAll(getListGroup())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Kembali ke MainActivity (tidak perlu memanggil AboutActivity)
                finish() // Menutup MainActivity dan kembali ke Activity sebelumnya (biasanya MainActivity)
                true
            }
            R.id.menu_about -> {
                // Panggil AboutActivity
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListGroup(): Collection<Group> {
        val dataGroupName =
            resources.getStringArray(R.array.riskianaomi_name)
        val dataGroupIcon =
            resources.obtainTypedArray(R.array.riskianaomi_icon)
        val dataGroupDescription =
            resources.getStringArray(R.array.book_description)

        val listGroup = ArrayList<Group>()
        for (i in dataGroupName.indices) {
            val group = Group(dataGroupName[i], dataGroupIcon.getResourceId(i, -1), dataGroupDescription[i])

            listGroup.add(group)
        }
        return listGroup
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.bookgroup.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.bookgroup.layoutManager =
                LinearLayoutManager(this)
        }

        val listGroupAdapter = ListGroupAdapter(dataGroup)
        binding.bookgroup.adapter = listGroupAdapter

        listGroupAdapter.setOnItemClickCallback(object :
            ListGroupAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Group) {
                showSelectedGroup(data)
            }
        })
    }

    private fun showSelectedGroup(group: Group) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_GROUP, group)
        startActivity(intentWithData)
    }

}
