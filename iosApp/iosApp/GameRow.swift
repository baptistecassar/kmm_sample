//
//  GameRow.swift
//  iosApp
//
//  Created by baptiste cassar on 04/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI
import shared


struct GameRow: View {
    let utils = Utils()
    
    var game: Game
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 25.0, style: .continuous).fill(Color.white)
            
            VStack{
                Text("gameType: \(game.gameType)")
                    .font(.headline)
                Spacer()
                Text("winning score: \(game.winningScore ?? 0)")
                    .font(.body)
                    .multilineTextAlignment(/*@START_MENU_TOKEN@*/.leading/*@END_MENU_TOKEN@*/)
                Text("winning players: \(utils.concatenatePlayers(players: game.winningPlayers))")
                    .font(.body)
                    .multilineTextAlignment(/*@START_MENU_TOKEN@*/.leading/*@END_MENU_TOKEN@*/)
                Spacer()
                Text("loosing score: \(game.loosingScore ?? 0)")
                    .font(.body)
                    .multilineTextAlignment(.trailing)
                Text("loosing players: \(utils.concatenatePlayers(players: game.loosingPlayers))")
                    .font(.body)
                    .multilineTextAlignment(/*@START_MENU_TOKEN@*/.leading/*@END_MENU_TOKEN@*/)
            }
            .padding(20)
        }
    }
}
