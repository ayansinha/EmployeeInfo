package org.techm.employeeinfo.ui.employee.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list_employee.*
import org.dtransform.techm.data.network.ServiceBuilder
import org.techm.employeeinfo.R
import org.techm.employeeinfo.data.model.employeedisplay.DataModel
import org.techm.employeeinfo.data.model.employeedisplay.EmployeeDataModel
import org.techm.employeeinfo.ui.employee.adapter.EmployeeListAdapter
import org.techm.employeeinfo.util.Constants
import org.techm.employeeinfo.util.isConnection
import org.techm.employeeinfo.util.showSnackBarView
import org.techm.employeeinfo.util.startNewActivity
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


class EmployeeListActivity : AppCompatActivity(), EmployeeListAdapter.OnItemClick,
    EmployeeListAdapter.OnLongItemClick {

    private val disposable = CompositeDisposable()
    var listEmployee = ArrayList<DataModel>()
    var displayListEmployee = ArrayList<DataModel>()
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_employee)
        setSupportActionBar(toolBar)

        init()
        loadEmployeeData()

        val itemTouchHelperCallBack =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                    val index = viewHolder.adapterPosition + 1
                    Toast.makeText(
                        this@EmployeeListActivity,
                        "position: $index",
                        Toast.LENGTH_SHORT
                    ).show()
                    (viewAdapter as EmployeeListAdapter).removeItem(viewHolder.adapterPosition)
                    deleteEmployeeById(index)
                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun deleteEmployeeById(index: Int) {
        if (isConnection()) {
            disposable.add(ServiceBuilder.buildService().deleteEmployeeByID(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.e("result status -> ", "${result.status}")
                        Log.e("result message ->", "${result.message}")
                        onSuccess(result.message)
                    },
                    { err ->
                        err.message?.let {
                            onError(err.message.toString())
                        }
                    }
                ))
        }else {
            recyclerView.showSnackBarView(Constants.NO_CONNECTION)
        }

    }

    private fun onSuccess(message: String) {
        recyclerView.showSnackBarView(message)
        recyclerView.adapter?.notifyDataSetChanged()
        /*dataBinding.recyclerView.showSnackBarView(message)
        dataBinding.recyclerView.adapter?.notifyDataSetChanged()*/
    }

    private fun onError(message: String) {
        recyclerView.showSnackBarView(message)
        loadEmployeeData()
        recyclerView.adapter?.notifyDataSetChanged()
    }


    /**
     * initializing views
     */
    private fun init() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@EmployeeListActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@EmployeeListActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    /**
     * load employee list
     */
    private fun loadEmployeeData() {

        if (isConnection()) {
            disposable.add(ServiceBuilder.buildService().getEmployeesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { res ->
                    //listEmployee.clear()
                    displayListEmployee.addAll(res.data)
                    listEmployee.addAll(res.data)
                    Timber.d("response -> $res")
                    onSuccessResponse(res)
                })
        }else {
            recyclerView.showSnackBarView(Constants.NO_CONNECTION)
        }
    }

    private fun onSuccessResponse(response: EmployeeDataModel) {
        Timber.d("response -> $response")
        Log.d("response -> ", " $response")
        recyclerView.adapter =
            EmployeeListAdapter(response.data, this@EmployeeListActivity)
        viewAdapter = EmployeeListAdapter(response.data, this@EmployeeListActivity)
    }

    private fun onErrorResponse(t: Throwable) {
        Timber.d("response -> ${t.message}")
        Log.d("response -> ", " ${t.message}")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.actionSearch)
        val searchV = searchItem?.actionView as SearchView
        searchV.imeOptions = EditorInfo.IME_ACTION_DONE
        val editTextSearch = searchV.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        editTextSearch.apply {
            this.hint = "Search here..."
            this.setHintTextColor(Color.WHITE)
        }

        /**
         * search query by employee name
         */
        searchV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    displayListEmployee.clear()
                    val searchQuery = newText.toLowerCase(Locale.getDefault())
                    listEmployee.forEach {
                        if (it.employee_name.toLowerCase(Locale.getDefault()).contentEquals(searchQuery)) {
                            displayListEmployee.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    displayListEmployee.clear()
                    displayListEmployee.addAll(listEmployee)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    /*private fun fromView(searchView: SearchView): Observable<String>? {
        val subject = PublishSubject.create<String>()
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                subject.onComplete()
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                subject.onNext(text)
                return true
            }
        })
        return subject
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionAdd -> {
                startNewActivity<EmployeeAddActivity>()
                true
            }
            R.id.actionSearch -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onClick(position: Int) {

    }

    override fun onLongClick(position: Int) {
        listEmployee.removeAt(position)
    }

}