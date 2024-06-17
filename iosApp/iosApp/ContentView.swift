import SwiftUI
import shared

struct ContentView: View {
    private let component: MainViewControllerComponent

    init(component: MainViewControllerComponent) {
        self.component = component
    }

    var body: some View {
        ComposeView(component: self.component)
            .ignoresSafeArea(.all, edges: .all)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    private let component: MainViewControllerComponent

    init(component: MainViewControllerComponent) {
        self.component = component
    }

    func makeUIViewController(context _: Context) -> UIViewController {
        component.uiViewControllerFactory()
    }

    func updateUIViewController(_: UIViewController, context _: Context) {}
}
