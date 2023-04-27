package com.example.rx_java

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class RxBus {
    companion object{
        val behaviorSubject = BehaviorSubject.create<String>()
    }
}