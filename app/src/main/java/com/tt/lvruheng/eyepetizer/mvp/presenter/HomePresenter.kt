package com.tt.lvruheng.eyepetizer.mvp.presenter

import android.content.Context
import com.tt.lvruheng.eyepetizer.mvp.contract.HomeContract
import com.tt.lvruheng.eyepetizer.mvp.model.HomeModel
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean
import com.tt.lvruheng.eyepetizer.utils.applySchedulers
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.Consumer
import io.reactivex.disposables.Disposable



/**
 * Created by lvruheng on 2017/7/5.
 */
class HomePresenter(context: Context,data : Long,view : HomeContract.View) : HomeContract.Presenter{
    var mContext : Context? = null
    var mView : HomeContract.View? = null
    val mModel : HomeModel by lazy {
        HomeModel()
    }
    init {
        mView = view
        mContext = context
    }
    override fun start() {
        requestData()
    }

    override fun requestData() {
       val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it,true,0) }
        observable?.applySchedulers()?.subscribe { homeBean : HomeBean ->
            homeBean.issueList?.forEach {
                it.itemList?.filter { it.type != "video" }
            }
            mView?.setData(homeBean)
        }
    }
    fun moreData(){

    }


}




