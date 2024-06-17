import SwiftUI
import shared

@main
struct WildlifeCarersApp: App {
    let appComponent: ApplicationComponent

    init() {
        self.appComponent = createAppComponent()
    }
    
    var body: some Scene {
        let mainViewControllerComponent = createMainViewControllerComponent(applicationComponent: self.appComponent)
        
        WindowGroup {
            ContentView(component: mainViewControllerComponent)
        }
    }
}

private func createMainViewControllerComponent(applicationComponent: ApplicationComponent) -> MainViewControllerComponent {
    MainViewControllerComponent.companion.create(applicationComponent: applicationComponent)
}

private func createAppComponent() -> ApplicationComponent {
    ApplicationComponent.companion.create()
}
