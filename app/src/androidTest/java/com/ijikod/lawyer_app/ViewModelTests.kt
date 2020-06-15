package com.ijikod.lawyer_app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.ijikod.lawyer_app.Data.Model.Lawyer
import com.ijikod.lawyer_app.Data.Repository
import com.ijikod.lawyer_app.ui.LawyersListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class ViewModelTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var vm : LawyersListViewModel

    @Mock
    lateinit var repo : Repository


    private  val observer: Observer<List<Lawyer>> = TestUtils().mock()


    @Before
    fun setUp(){
        repo = Repository(ApplicationProvider.getApplicationContext())
        vm = LawyersListViewModel(ApplicationProvider.getApplicationContext())
    }


    @Test
    fun testModelStateChange(){
        vm.lawyerData.observeForever(observer)

        vm.lawyerData.value?.isNotEmpty()?.let { assert(it) }
    }
}