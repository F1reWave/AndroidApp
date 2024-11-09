package ru.firevawe.firevawemobile.presentation.utils

import androidx.navigation.NavController
import ru.firevawe.firevawemobile.presentation.navigation.ScreenRoute

internal fun NavController.navigateeeee(route: ScreenRoute) {
    navigate(route = route.route) {
        popUpTo(currentBackStackEntry?.destination?.id ?: 0) { inclusive = true }
        launchSingleTop = true // Запускаем новый экран, если он уже в верхней части стека
    }
}