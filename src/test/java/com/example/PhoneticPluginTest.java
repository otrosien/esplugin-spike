package com.example;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.inject.Injector;
import org.elasticsearch.common.inject.ModulesBuilder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.PluginsModule;
import org.elasticsearch.plugins.PluginsService;
import org.junit.Test;

public class PhoneticPluginTest {

    @Test
    public void pluginLoads() {
        Settings settings = Settings.builder().build();
        List<Class<? extends Plugin>> plugins = new ArrayList<>();
        plugins.add(AnalysisPhoneticPlugin.class);
        PluginsService pluginsService = new PluginsService(
                settings, null, null, plugins);

        ModulesBuilder modules = new ModulesBuilder();
        modules.add(new PluginsModule(pluginsService));
        pluginsService.processModules(modules);
        Injector injector = modules.createInjector();
        PhoneticAnalysisBinderProcessor phoneticProcessor = injector.getInstance(PhoneticAnalysisBinderProcessor.class);
        assertThat(phoneticProcessor, notNullValue());
    }

}
