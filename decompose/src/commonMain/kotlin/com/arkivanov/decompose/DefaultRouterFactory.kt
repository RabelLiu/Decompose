package com.arkivanov.decompose

import com.arkivanov.decompose.backpressed.BackPressedRegistry
import com.arkivanov.decompose.instancekeeper.InstanceKeeper
import com.arkivanov.decompose.lifecycle.Lifecycle
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.StateKeeper
import kotlin.reflect.KClass

internal class DefaultRouterFactory(
    private val lifecycle: Lifecycle,
    private val stateKeeper: StateKeeper,
    private val instanceKeeper: InstanceKeeper,
    private val backPressedRegistry: BackPressedRegistry
) : RouterFactory {

    override fun <C : Parcelable, T : Any> router(
        initialConfiguration: () -> C,
        initialBackStack: () -> List<C>,
        configurationClass: KClass<out C>,
        key: String,
        handleBackButton: Boolean,
        componentFactory: (configuration: C, ComponentContext) -> T
    ): Router<C, T> =
        RouterImpl(
            initialConfiguration = initialConfiguration,
            initialBackStack = initialBackStack,
            configurationClass = configurationClass,
            lifecycle = lifecycle,
            key = key,
            stateKeeper = stateKeeper,
            instanceKeeper = instanceKeeper,
            backPressedRegistry = backPressedRegistry,
            popOnBackPressed = handleBackButton
        ) { configuration, lifecycle, stateKeeperProvider, instanceKeeperProvider, backPressedDispatcher ->
            componentFactory(
                configuration,
                DefaultComponentContext(
                    lifecycle,
                    stateKeeperProvider,
                    instanceKeeperProvider,
                    backPressedDispatcher
                )
            )
        }
}
