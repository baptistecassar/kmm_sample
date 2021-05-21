import SwiftUI
import shared

struct ContentView: View {
  @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("PINCH Games")
            .navigationBarItems(trailing:
                Button("Reload") {
                    self.viewModel.loadGames(forceReload: true)
            })
        }
    }

    private func listView() -> AnyView {
        switch viewModel.games {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let games):
            return AnyView(List(games) { game in
                GameRow(game: game)
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView {

    enum LoadableGames {
        case loading
        case result([Game])
        case error(String)
    }

    class ViewModel: ObservableObject {
        let gameRepository: GameRepository
        @Published var games = LoadableGames.loading

        init(gameRepository: GameRepository) {
            self.gameRepository = gameRepository
            self.loadGames(forceReload: false)
        }

        func loadGames(forceReload: Bool) {
            self.games = .loading
            gameRepository.getGames(completionHandler: { games, error in
                if let games = games {
                    self.games = .result(games)
                } else {
                    self.games = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}

extension Game: Identifiable { }
