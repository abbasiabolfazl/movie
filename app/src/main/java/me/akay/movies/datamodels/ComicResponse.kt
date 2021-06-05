package me.akay.movies.datamodels

data class ComicResponse(
    val copyright: String? = null,
    val code: Int? = null,
    val data: ComicData? = null,
    val attributionHTML: String? = null,
    val attributionText: String? = null,
    val etag: String? = null,
    val status: String? = null
)

data class DatesItem(
    val date: String? = null,
    val type: String? = null
)

data class Creators(
    val collectionURI: String? = null,
    val available: Int? = null,
    val returned: Int? = null,
    val items: List<ItemsItem?>? = null
)

data class TextObjectsItem(
    val language: String? = null,
    val text: String? = null,
    val type: String? = null
)

data class ImagesItem(
    val path: String? = null,
    val extension: String? = null
)

data class Comic(
    val creators: Creators? = null,
    val issueNumber: Int? = null,
    val isbn: String? = null,
    val description: String? = null,
    val variants: List<Any?>? = null,
    val title: String? = null,
    val diamondCode: String? = null,
    val characters: Characters? = null,
    val urls: List<UrlsItem?>? = null,
    val ean: String? = null,
    val collections: List<Any?>? = null,
    val modified: String? = null,
    val id: Int? = null,
    val prices: List<PricesItem?>? = null,
    val events: Events? = null,
    val collectedIssues: List<Any?>? = null,
    val pageCount: Int? = null,
    val thumbnail: Thumbnail? = null,
    val images: List<ImagesItem?>? = null,
    val stories: Stories? = null,
    val textObjects: List<TextObjectsItem?>? = null,
    val digitalId: Int? = null,
    val format: String? = null,
    val upc: String? = null,
    val dates: List<DatesItem?>? = null,
    val resourceURI: String? = null,
    val variantDescription: String? = null,
    val issn: String? = null,
    val series: Series? = null
)

data class Characters(
    val collectionURI: String? = null,
    val available: Int? = null,
    val returned: Int? = null,
    val items: List<ItemsItem?>? = null
)

data class ComicData(
    val total: Int? = null,
    val offset: Int? = null,
    val limit: Int? = null,
    val count: Int? = null,
    val results: List<Comic>? = null
)

data class VariantsItem(
    val name: String? = null,
    val resourceURI: String? = null
)

data class PricesItem(
    val price: Double? = null,
    val type: String? = null
)

