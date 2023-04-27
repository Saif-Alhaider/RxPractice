package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.SecondActivityBinding
import com.example.rx_java.RxBus.Companion.behaviorSubject
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SecondActivity : AppCompatActivity() {
    lateinit var binding: SecondActivityBinding
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = SecondActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun addCallBacks() {
        getCounter()
    }

    private fun getCounter() {
        behaviorSubject.subscribe { countString ->
            binding.textViewCount.text = countString
        }.add(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}