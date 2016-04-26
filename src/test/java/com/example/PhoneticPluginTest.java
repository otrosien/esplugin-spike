package com.example;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.elasticsearch.common.inject.Injector;
import org.elasticsearch.common.inject.ModulesBuilder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.PluginsModule;
import org.elasticsearch.plugins.PluginsService;
import org.junit.Test;

import com.example.AnalysisPhoneticPlugin;
import com.example.PhoneticAnalysisBinderProcessor;

public class PhoneticPluginTest {

    @Test
    public void pluginLoads() {
        Settings settings = Settings.builder().build();
        PluginsService pluginsService = new PluginsService(settings, null, null,
                Arrays.asList(AnalysisPhoneticPlugin.class));

        ModulesBuilder modules = new ModulesBuilder();
        modules.add(new PluginsModule(pluginsService));
        pluginsService.processModules(modules);
        Injector injector = modules.createInjector();
        PhoneticAnalysisBinderProcessor phoneticProcessor = injector.getInstance(PhoneticAnalysisBinderProcessor.class);
        assertThat(phoneticProcessor, notNullValue());
    }

}
