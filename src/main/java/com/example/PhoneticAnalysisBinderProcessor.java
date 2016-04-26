package com.example;

import org.elasticsearch.index.analysis.AnalysisModule;

public class PhoneticAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override
    public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
        tokenFiltersBindings.processTokenFilter("phonetic", PhoneticTokenFilterFactory.class);
    }

}
