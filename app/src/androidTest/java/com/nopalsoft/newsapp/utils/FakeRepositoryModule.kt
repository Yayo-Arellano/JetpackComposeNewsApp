package com.nopalsoft.newsapp.utils

import com.nopalsoft.newsapp.di.RepositoryModule
import com.nopalsoft.newsapp.model.News
import com.nopalsoft.newsapp.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    @Singleton
    fun providerNewsRepository(): NewsRepository =
        object : NewsRepository {
            val news = arrayListOf(
                News("Title1", "Content1", "Author1", "Url1", "urlImage1"),
                News("Title2", "Content2", "Author2", "Url2", "urlImage2")
            )

            override suspend fun getNews(country: String): List<News> = news

            override fun getNew(title: String): News = news[0]
        }
}