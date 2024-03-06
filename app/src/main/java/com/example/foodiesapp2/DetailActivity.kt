package com.example.foodiesapp2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val key_hero = "key_hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgDetailPhoto: ImageView = findViewById(R.id.img_detail_photo)
        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val btnDetailShare: Button = findViewById(R.id.btn_detail_share)
        val tvWeight: TextView = findViewById(R.id.tv_detail_weight)
        val tvLikes: TextView = findViewById(R.id.tv_detail_likes)
        btnDetailShare.setOnClickListener(this)

        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Hero>(key_hero, Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hero>(key_hero)
        }

        if (dataHero != null) {
            tvDetailName.text = dataHero.name
            tvDetailDescription.text = dataHero.description
            imgDetailPhoto.setImageResource(dataHero.photo)
            tvWeight.text = dataHero.weight
            tvLikes.text = (dataHero.likes + " Likes")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_back -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_detail_share -> {
                val dataHero = if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra<Hero>(key_hero, Hero::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<Hero>(key_hero)
                }
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "This is my favorite food ${dataHero?.name}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}
