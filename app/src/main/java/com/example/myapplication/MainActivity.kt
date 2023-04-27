package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.rx_java.RxBus.Companion.behaviorSubject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val clickSubject = BehaviorSubject.create<Unit>()
    private var compositeDisposable = CompositeDisposable()
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addCallBacks()
    }

    fun addCallBacks() {
        countNumber()
        setData()
    }

    private fun countNumber() {
        binding.buttonIncremeant.setOnClickListener {
            count++
        }
    }

    private fun setData() {
        binding.buttonGo.setOnClickListener {
            behaviorSubject.onNext("$count")
            clickSubject.onNext(Unit)
        }
        goToSecond()
    }

    @SuppressLint("CheckResult")
    private fun goToSecond() {
        clickSubject.throttleFirst(2, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(this,SecondActivity::class.java))
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
    companion object{
        const val TAG = "TEST"
    }
}