package com.example.flutternativeimage;

import android.content.Context;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

/**
 * FlutterNativeImagePlugin
 */
public class FlutterNativeImagePlugin implements FlutterPlugin {
    private static final String CHANNEL_NAME = "flutter_native_image";
    private MethodChannel channel;

    /**
     * Plugin registration.
     */
    // Remove the old registerWith method
    // public static void registerWith(PluginRegistry.Registrar registrar) {
    //     final FlutterNativeImagePlugin plugin = new FlutterNativeImagePlugin();
    //     plugin.setupChannel(registrar.messenger(), registrar.context());
    // }

    @Override
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
        // Updated to use FlutterPluginBinding to attach to the engine
        setupChannel(binding.getFlutterEngine().getDartExecutor(), binding.getApplicationContext());
    }

    @Override
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        // Handle detaching from engine if needed
        teardownChannel();
    }

    private void setupChannel(BinaryMessenger messenger, Context context) {
        channel = new MethodChannel(messenger, CHANNEL_NAME);
        MethodCallHandlerImpl handler = new MethodCallHandlerImpl(context);
        channel.setMethodCallHandler(handler);
    }

    private void teardownChannel() {
        channel.setMethodCallHandler(null);
        channel = null;
    }
}
