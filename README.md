# FinalProject
Submission for CIS263AA Final Project.
I chose to make a chess game for my final.
If your king is being attacked directly by an opposing piece that can take the king if no action is taken, you are in "check". If you are in check you must take some action to stop being in check.
To win, trap your opponents king such that they cannot make a move without their king being captured. This is called "checkmate". (read Stalemate*).
You capture, or remove, an enemy piece by placing your piece on top of theirs.
Pawns can move one square forwards, or two if it's their first move. They can only capture diagonally forwards. (read En Passant*).
Bishops can move to any space diagonal from them.
Knights can move two spaces away horizontally and one space vertically, or two spaces away vertically and one space horizontally (similar to an L). Knights can jump over other pieces.
Rooks can move to any space horizontal or vertical from them.
Queens can move to any space horizontal, vertical, or diagonal from them.
Kings can move to any of the 8 adjacent spaces. (read Castling*).
*En Passant: If a pawn moves two spaces on its first move, for ONLY the next turn the pawn can be captured by enemy pawns as though it had only been moved one square.
*Castling: A king can move two spaces and place the rook it moves towards on the other side of the king, IF the king hasn't moved, the rook the king moves towards hasn't move, and there are no pieces in the way.
Draws:
*Stalemate occurs when a player has no available moves that won't put their king in danger, but their king is not currently targeted by a piece.
Threefold Repetition occurs when a state on the board is reached three times; this is so that the game can end before the 50-move rule.
Lack of Material on either side, such that nobody can claim a win, will result in a draw.
The 50-Move Rule ensures that games can't drag on indefinitely. After 50 moves without a pawn advancing or a piece being captured the game ends in a draw.
