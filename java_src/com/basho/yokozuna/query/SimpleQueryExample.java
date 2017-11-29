/*
 * This is a simple query example to show that querying Yokozuna with
 * a standard Solr client works.
 *
 * Usage:
 *
 * java -cp priv/java_lib/yokozuna.jar:priv/solr-jars/WEB-INF/lib/* com.basho.yokozuna.query.SimpleQueryExample BASE_URL INDEX FIELD TERM
 *
 * Example:
 *
 * java -cp priv/java_lib/yokozuna.jar:priv/solr-jars/WEB-INF/lib/* com.basho.yokozuna.query.SimpleQueryExample http://localhost:8098/search fruit text apple
 */

package com.basho.yokozuna.query;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.common.params.ModifiableSolrParams;
import java.lang.StringBuilder;

public class SimpleQueryExample {

    public static void main(String[] args) throws SolrServerException {
        final String baseURL = args[0];
        final String index = args[1];
        final String field = args[2];
        final String term = args[3];

        final SolrClient solr = new HttpSolrClient.Builder(baseURL + "/" + index).build();
        final ModifiableSolrParams params = new ModifiableSolrParams();
        params.set("qt", "/");
        params.set("q", field + ":" + term);
        final SolrRequest req = new QueryRequest(params);

        final QueryResponse resp = solr.query(params);
        System.out.println("resp: " + resp);
    }
}
