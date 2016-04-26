package com.example;

import static org.elasticsearch.common.settings.Settings.settingsBuilder;
import static org.hamcrest.CoreMatchers.instanceOf;

import org.elasticsearch.Version;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.inject.Injector;
import org.elasticsearch.common.inject.ModulesBuilder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsModule;
import org.elasticsearch.env.Environment;
import org.elasticsearch.env.EnvironmentModule;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.IndexNameModule;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.AnalysisService;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.index.settings.IndexSettingsModule;
import org.elasticsearch.indices.analysis.IndicesAnalysisService;
import org.elasticsearch.test.ESTestCase;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.example.PhoneticAnalysisBinderProcessor;
import com.example.PhoneticTokenFilterFactory;


public class SimplePhoneticAnalysisTests extends ESTestCase {

    @Test
    public void testPhoneticTokenFilterFactory() {
        String yaml = "/org/elasticsearch/index/analysis/phonetic-1.yml";
        Settings settings = settingsBuilder().loadFromStream(yaml, getClass().getResourceAsStream(yaml))
                .put(IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT)
                .put("path.home", createTempDir())
                .build();
        AnalysisService analysisService = testSimpleConfiguration(settings);
        TokenFilterFactory filterFactory = analysisService.tokenFilter("phonetic");
        MatcherAssert.assertThat(filterFactory, instanceOf(PhoneticTokenFilterFactory.class));
    }

    private AnalysisService testSimpleConfiguration(Settings settings) {
        Index index = new Index("test");

        Injector parentInjector = new ModulesBuilder().add(
                new SettingsModule(settings),
                new EnvironmentModule(new Environment(settings))).createInjector();
        Injector injector = new ModulesBuilder().add(
                new IndexSettingsModule(index, settings),
                new IndexNameModule(index),
                new AnalysisModule(settings, parentInjector.getInstance(IndicesAnalysisService.class))
                .addProcessor(new PhoneticAnalysisBinderProcessor())).createChildInjector(parentInjector);

        AnalysisService analysisService = injector.getInstance(AnalysisService.class);
        return analysisService;
    }
}
