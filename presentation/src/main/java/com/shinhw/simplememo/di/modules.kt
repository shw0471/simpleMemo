package com.shinhw.simplememo.di

import androidx.room.Room
import com.shinhw.data.datasource.MemoDataSource
import com.shinhw.data.datasource.MemoDataSourceImpl
import com.shinhw.data.local.MemoDatabase
import com.shinhw.data.mapper.MemoEntityMapper
import com.shinhw.data.repository.MemoRepositoryImpl
import com.shinhw.domain.repository.MemoRepository
import com.shinhw.domain.service.MemoService
import com.shinhw.domain.service.MemoServiceImpl
import com.shinhw.domain.usecase.AddMemoUseCase
import com.shinhw.domain.usecase.DeleteMemoUseCase
import com.shinhw.domain.usecase.EditMemoUseCase
import com.shinhw.domain.usecase.GetMemoUseCase
import com.shinhw.simplememo.presentation.viewmodel.MemoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    // Local
    single {
        Room.databaseBuilder(get(), MemoDatabase::class.java, "memo.db")
            .allowMainThreadQueries()
            .build()
    }

    single { get<MemoDatabase>().memoDao() }

    // Mapper
    single { MemoEntityMapper() }

    // DataSource
    single<MemoDataSource> { MemoDataSourceImpl(get()) }

    // Repository
    single<MemoRepository> { MemoRepositoryImpl(get(), get()) }

    // Service
    single<MemoService> { MemoServiceImpl(get()) }

    // UseCase
    single { GetMemoUseCase(get()) }
    single { AddMemoUseCase(get()) }
    single { EditMemoUseCase(get()) }
    single { DeleteMemoUseCase(get()) }

    // ViewModel
    viewModel { MemoViewModel(get(), get(), get(), get()) }
}