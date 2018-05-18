package com.yunyihenkey.supplier.service;

import org.apache.solr.common.SolrDocumentList;

import java.util.Map;

public interface SearchService {

    Map<String, Object> search(String queryString, int pageNum, int pageSize);
}
