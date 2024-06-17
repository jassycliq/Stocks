package com.jassycliq.stocks.di

import com.jassycliq.stocks.api.ApiComponent

expect interface PlatformNetworkComponent

interface NetworkComponent : PlatformNetworkComponent, ApiComponent
