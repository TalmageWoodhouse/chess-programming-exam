package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private PieceType type;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPieceMoveCalculator moves = switch (getPieceType()) {
            case ROOK -> new RookMoveCalc();
            case BISHOP -> new BishopMoveCalc();
            case QUEEN -> new QueenMoveCalc();
            case KING -> new KingMoveCalc();
            case KNIGHT -> new KnightMoveCalc();
            case PAWN -> new PawnMoveCalc();
            default -> throw new RuntimeException("invalid imput type");
        };
        return moves.pieceMoves(board, myPosition);
    }

    public interface ChessPieceMoveCalculator {
        Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);
    }

    public static class RookMoveCalc implements ChessPieceMoveCalculator {
        @Override
        public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
            Collection<ChessMove> moves = new ArrayList<>();
            int[][] rookMoveDirections = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
            };
            int row = myPosition.getRow();
            int col = myPosition.getColumn();
            for (int[] direction : rookMoveDirections) {
                row += direction[0];
                col += direction[1];
                ChessPosition pos = new ChessPosition(row,col);
                while (board.isValidMove(pos) && !board.isFriendly(myPosition, pos)) {
                    if (board.isEnemy(myPosition, pos)) {
                        moves.add(new ChessMove(myPosition, pos, null));
                        break;
                    }
                    moves.add(new ChessMove(myPosition, pos, null));
                    row += direction[0];
                    col += direction[1];
                    pos = new ChessPosition(row, col);
                }
                row = myPosition.getRow();
                col = myPosition.getColumn();
            }
            return moves;
        }
    }

    public static class BishopMoveCalc implements ChessPieceMoveCalculator {
        @Override
        public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
            Collection<ChessMove> moves = new ArrayList<>();
            int[][] rookMoveDirections = {
                    {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
            };
            int row = myPosition.getRow();
            int col = myPosition.getColumn();
            for (int[] direction : rookMoveDirections) {
                row += direction[0];
                col += direction[1];
                ChessPosition pos = new ChessPosition(row, col);
                while (board.isValidMove(pos) && !board.isFriendly(myPosition, pos)) {
                    if (board.isEnemy(myPosition, pos)) {
                        moves.add(new ChessMove(myPosition, pos, null));
                        break;
                    }
                    moves.add(new ChessMove(myPosition, pos, null));
                    row += direction[0];
                    col += direction[1];
                    pos = new ChessPosition(row, col);
                }
                row = myPosition.getRow();
                col = myPosition.getColumn();
            }
            return moves;
        }
    }

    public static class QueenMoveCalc implements ChessPieceMoveCalculator {
        @Override
        public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
            Collection<ChessMove> moves = new ArrayList<>();
            int[][] rookMoveDirections = {
                    {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                    {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
            };
            int row = myPosition.getRow();
            int col = myPosition.getColumn();
            for (int[] direction : rookMoveDirections) {
                row += direction[0];
                col += direction[1];
                ChessPosition pos = new ChessPosition(row, col);
                while (board.isValidMove(pos) && !board.isFriendly(myPosition, pos)) {
                    if (board.isEnemy(myPosition, pos)) {
                        moves.add(new ChessMove(myPosition, pos, null));
                        break;
                    }
                    moves.add(new ChessMove(myPosition, pos, null));
                    row += direction[0];
                    col += direction[1];
                    pos = new ChessPosition(row, col);
                }
                row = myPosition.getRow();
                col = myPosition.getColumn();
            }
            return moves;
        }
    }

    public static class KingMoveCalc implements ChessPieceMoveCalculator {
        @Override
        public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
            Collection<ChessMove> moves = new ArrayList<>();
            int[][] rookMoveDirections = {
                    {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                    {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
            };
            int row = myPosition.getRow();
            int col = myPosition.getColumn();
            for (int[] direction : rookMoveDirections) {
                row += direction[0];
                col += direction[1];
                ChessPosition pos = new ChessPosition(row, col);
                if (board.isValidMove(pos) && !board.isFriendly(myPosition, pos)) {
                    moves.add(new ChessMove(myPosition, pos, null));
                }
                row = myPosition.getRow();
                col = myPosition.getColumn();
            }
            return moves;
        }
    }

    public static class KnightMoveCalc implements ChessPieceMoveCalculator {
        @Override
        public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
            Collection<ChessMove> moves = new ArrayList<>();
            int[][] rookMoveDirections = {
                    {2, 1}, {2, -1}, {1, 2}, {1, -2},
                    {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}

            };
            int row = myPosition.getRow();
            int col = myPosition.getColumn();
            for (int[] direction : rookMoveDirections) {
                row += direction[0];
                col += direction[1];
                ChessPosition pos = new ChessPosition(row, col);
                if (board.isValidMove(pos) && !board.isFriendly(myPosition, pos)) {
                    moves.add(new ChessMove(myPosition, pos, null));
                }
                row = myPosition.getRow();
                col = myPosition.getColumn();
            }
            return moves;
        }
    }

    public static class PawnMoveCalc implements ChessPieceMoveCalculator {
        @Override
        public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
            Collection<ChessMove> moves = new ArrayList<>();
            int initialRow = (board.getPiece(myPosition).pieceColor == ChessGame.TeamColor.WHITE) ? 2:7;
            int promotionRow = (board.getPiece(myPosition).pieceColor == ChessGame.TeamColor.WHITE) ? 8:1;


            int row = myPosition.getRow();
            int col = myPosition.getColumn();

            if (board.getPiece(myPosition).pieceColor == ChessGame.TeamColor.WHITE) {
                // step forward
                ChessPosition frontPos = new ChessPosition(row + 1, col);
                if (board.isValidMove(frontPos) && !board.isFriendly(myPosition, frontPos)
                    && !board.isEnemy(myPosition, frontPos)) {
                    if (row + 1 == promotionRow) {
                        moves.add(new ChessMove(myPosition, frontPos, PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, frontPos, PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, frontPos, PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, frontPos, PieceType.QUEEN));
                    } else {
                        moves.add(new ChessMove(myPosition, frontPos, null));
                    }
                }
                //right attack
                ChessPosition rightPos = new ChessPosition(row + 1, col + 1);
                if (board.isValidMove(rightPos) && board.isEnemy(myPosition, rightPos)) {
                    if (row + 1 == promotionRow) {
                        moves.add(new ChessMove(myPosition, rightPos, PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, rightPos, PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, rightPos, PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, rightPos, PieceType.QUEEN));
                    } else {
                        moves.add(new ChessMove(myPosition, rightPos, null));
                    }
                }

                //left attack
                ChessPosition leftPos = new ChessPosition(row + 1, col - 1);
                if (board.isValidMove(leftPos) && board.isEnemy(myPosition, leftPos)) {
                    if (row + 1 == promotionRow) {
                        moves.add(new ChessMove(myPosition, leftPos, PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, leftPos, PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, leftPos, PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, leftPos, PieceType.QUEEN));
                    } else {
                        moves.add(new ChessMove(myPosition, leftPos, null));
                    }
                }

                //twostep
                ChessPosition twoStep = new ChessPosition(row + 2, col);
                if (board.isValidMove(twoStep) && !board.isFriendly(myPosition, frontPos)
                        && !board.isEnemy(myPosition, frontPos)
                        && !board.isEnemy(myPosition, twoStep)
                        && !board.isEnemy(myPosition, twoStep)
                        && row == initialRow) {
                        moves.add(new ChessMove(myPosition, twoStep, null));
                }
            }
            //Black moves
            if (board.getPiece(myPosition).pieceColor == ChessGame.TeamColor.BLACK) {
                // step forward
                ChessPosition frontPos = new ChessPosition(row - 1, col);
                if (board.isValidMove(frontPos) && !board.isFriendly(myPosition, frontPos)
                        && !board.isEnemy(myPosition, frontPos)) {
                    if (row - 1 == promotionRow) {
                        moves.add(new ChessMove(myPosition, frontPos, PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, frontPos, PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, frontPos, PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, frontPos, PieceType.QUEEN));
                    } else {
                        moves.add(new ChessMove(myPosition, frontPos, null));
                    }
                }
                //right attack
                ChessPosition rightPos = new ChessPosition(row - 1, col - 1);
                if (board.isValidMove(rightPos) && board.isEnemy(myPosition, rightPos)) {
                    if (row - 1 == promotionRow) {
                        moves.add(new ChessMove(myPosition, rightPos, PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, rightPos, PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, rightPos, PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, rightPos, PieceType.QUEEN));
                    } else {
                        moves.add(new ChessMove(myPosition, rightPos, null));
                    }
                }

                //left attack
                ChessPosition leftPos = new ChessPosition(row - 1, col + 1);
                if (board.isValidMove(leftPos) && board.isEnemy(myPosition, leftPos)) {
                    if (row - 1 == promotionRow) {
                        moves.add(new ChessMove(myPosition, leftPos, PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, leftPos, PieceType.KNIGHT));
                        moves.add(new ChessMove(myPosition, leftPos, PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, leftPos, PieceType.QUEEN));
                    } else {
                        moves.add(new ChessMove(myPosition, leftPos, null));
                    }
                }

                //twostep
                ChessPosition twoStep = new ChessPosition(row - 2, col);
                if (board.isValidMove(twoStep) && !board.isFriendly(myPosition, frontPos)
                        && !board.isEnemy(myPosition, frontPos)
                        && !board.isEnemy(myPosition, twoStep)
                        && !board.isEnemy(myPosition, twoStep)
                        && row == initialRow) {
                    moves.add(new ChessMove(myPosition, twoStep, null));
                }
            }
            return moves;
        }
    }


    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceColor=" + pieceColor +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}
