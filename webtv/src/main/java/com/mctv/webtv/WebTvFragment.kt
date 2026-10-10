package com.mctv.webtv

import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import com.mctv.core.CardPresenter
import com.mctv.core.Movie
import com.mctv.web.WebModule

class WebTvFragment : BrowseSupportFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        loadChannels()
    }

    private fun setupUI() {
        title = "WebTV"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
    }

    private fun loadChannels() {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val webChannels = WebModule.getChannels()

        if (webChannels.isNotEmpty()) {
            val adapter = ArrayObjectAdapter(CardPresenter().apply {
                onItemViewClickedListener = OnItemViewClickedListener { _, item, _, _ ->
                    val movie = item as? Movie ?: return@OnItemViewClickedListener
                    WebModule.navigate(requireContext(), movie)
                }
            })
            webChannels.forEach { channel -> adapter.add(channel) }
            rowsAdapter.add(ListRow(HeaderItem(0, WebModule.CAT_WEB), adapter))
        }

        adapter = rowsAdapter
    }
}
