package com.example.newsapp.ui.model

import androidx.compose.runtime.Stable

@Stable
data class NewsUiModel(
    val articles: List<NewsItemUiModel>,
    val status: String,
    val totalResults: Int
) {
    @Stable
    data class NewsItemUiModel(
        val id: Int?,
        val author: String?,
        val content: String?,
        val description: String?,
        val publishedAt: String,
        val source: String?,
        val title: String?,
        val url: String?,
        val urlToImage: String?
    ) {
        companion object {
            fun getDummyData() = NewsItemUiModel(
                id = 0,
                source = "Business Insider",
                author = "Theron Mohamed",
                title = "Autry Stephens sold his company for $26 billion but died before the deal was done. Here's how a Texas farm boy became an oil tycoon.",
                description = "Autry Stephens built Endeavor Energy into one of the biggest private oil producers and sold the company this spring, but died before the deal closed.",
                url = "https=//www.businessinsider.com/oil-deals-endeavor-diamondback-autry-stephens-company-sale-obituary-biography-2024-8",
                urlToImage = "https=//i.insider.com/66d0506a392a3bda9f22e9ef?width=1200&format=jpeg",
                publishedAt = "2024-10-29T12=13=55Z",
                content = "Autry Stephens became an oil tycoon in his forties.Endeavor Energy Resources\r\n<ul><li>Autry Stephens sold his oil company for $26 billion but died before the deal was completed.</li><li>The Texan far… [+4685 chars]"
            )
        }
    }

    companion object {
        fun getDummyData() =  NewsUiModel(
            status = "SUCCESS",
            totalResults = 10,
            articles = listOf(
                NewsItemUiModel(
                    id = 1,
                    author = "Al Jazeera",
                    content = "Yemens Houthis have released a video that appears to show their fighters boarding the oil tanker Sounion and detonating explosives on the ship that the group previously attacked in the Red Sea earlie… [+3203 chars]",
                    description = "Video appears to show a series of explosions on the vessel, amid fears of a potential major oil spill.",
                    publishedAt = "2024-08-29T18:42:54Z",
                    source = "Al Jazeera English",
                    title = "Houthis release footage of fighters boarding Greek oil tanker in Red Sea",
                    url = "https://www.aljazeera.com/news/2024/8/29/houthis-release-footage-of-fighters-boarding-greek-oil-tanker-in-red-sea",
                    urlToImage = "https://www.aljazeera.com/wp-content/uploads/2024/08/2024-08-28T192020Z_537945345_RC26P9AL6ODP_RTRMADP_3_ISRAEL-PALESTINIANS-SHIPPING-IRAN-1724953664.jpg?resize=1200%2C675"
                ),
                NewsItemUiModel(
                    id = 2,
                    author = "Leah Sarnoff",
                    content = "When Jill Antares Hunkler purchased land in Belmont County, Ohio, in 2007, she never envisioned her home would be surrounded by 78 oil and gas fracking wells a decade later, she said.\r\n I wanted to b…[+8398 chars]",
                    description = "In Ohio, oil and gas companies must prove at least 65% of property owners in a project area consent to leasing their land for drilling before going forward.",
                    publishedAt = "2024-08-29T16:07:45Z",
                    source = "ABC News",
                    title = "Why fracking can be forced on property owners' land in this state",
                    url = "https://abcnews.go.com/US/fracking-forced-ohio-property-owners-land-legal/story?id=113158873",
                    urlToImage = "https://i.abcnewsfe.com/a/533faae7-826d-4838-91d2-02d2f9b0548d/hunker-farm-fracking-ht-jef-2420828_1724859153842_hpMain_16x9.jpg?w=1600"
                ),
                NewsItemUiModel(
                    id = 3,
                    author = "Uncrate",
                    content = "Foria is a plant-based wellness brand that uses all-natural, organic formulas to boost sexual and female wellness. Highlights of their line include Awaken Arousal Oil with CBD, which serves to boost … [+471 chars]",
                    description = "Worn by Jeff Bridges as Jeffrey The Dude Lebowski in the final three scenes of The Big Lebowski — including the cliffside spreading Donnie's ashes scene — this bowling shirt is a piece of cinema history. It s now up for... ",
                    publishedAt = "2024-08-29T19:00:01Z",
                    source = "Uncrate.com",
                    title = "The Big Lebowski Screen-Worn Bowling Shirt & Shorts",
                    url = "https://uncrate.com/the-big-lebowski-screen-worn-bowling-shirt-shorts/",
                    urlToImage = "https://uncrate.com/p/2024/08/big-lebowski-bowling-shirt-shorts.jpg"
                ),

                NewsItemUiModel(
                    id = 4,
                    author = "Madison Hoff",
                    content = "CharlieChesvick/Getty Images\r\n",
                    description = "Based on projected increases from 2023 to 2033, wind turbine service technicians, data scientists, and actuaries are among the fastest-growing jobs.",
                    publishedAt = "2024-08-29T17:15:15Z",
                    source = "Business Insider",
                    title = "The 15 fastest-growing jobs in the US",
                    url = "https://www.businessinsider.com/fastest-growing-jobs-employment-projections-renewable-energy-healthcare-analysts-2024-8",
                    urlToImage = "https://i.insider.com/66d088c243b5e59d16b68456?width=1200&format=jpeg"
                ),
                NewsItemUiModel(
                    id = 5,
                    author = null,
                    content = "NewsFeed\r\nYemens Houthis have released video showing the armed group detonating explosives on the Greek-flagged Sounion oil tanker last week. The ship is currently stranded in the Red Sea and the US … [+35 chars]",
                    description = "Yemen's Houthis have released video showing the armed group detonating explosives on the Greek-flagged Sounion tanker.",
                    publishedAt = "2024-08-29T18:28:32Z",
                    source = "Al Jazeera English",
                    title = "Houthis release video of attack on Red Sea oil tanker",
                    url = "https://www.aljazeera.com/program/newsfeed/2024/8/29/houthis-release-video-of-attack-on-red-sea-oil-tanker",
                    urlToImage = "https://www.aljazeera.com/wp-content/uploads/2024/08/Telegram-army21ye.mp4.00_01_50_28.Still002-1724955713.jpg?resize=1920%2C1080&quality=80"
                ),
            )
        )
    }
}