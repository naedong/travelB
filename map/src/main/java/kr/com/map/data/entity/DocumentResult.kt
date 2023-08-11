package kr.com.map.data.entity

import kr.com.map.data.model.Document

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-11
 * Time: 오전 12:16
 */
data class DocumentResult(val documentList: List<Document>, val isMoveCamera: Boolean) {
    companion object {
        fun empty() = DocumentResult(documentList = emptyList(), isMoveCamera = false)
    }
}
