package Jaja;

import java.util.*;
public class SpotifyPlaylist {

	private static ArrayList<String> playlist = new ArrayList<>();
    private static Stack<ArrayList<String>> undoStack = new Stack<>();
    private static Stack<ArrayList<String>> redoStack = new Stack<>();
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n--- Spotify Playlist Menu ---");
            System.out.println("1. Add song");
            System.out.println("2. Remove last song");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. View playlist");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
            case 1 -> addSong(sc);
            case 2 -> removeSong();
            case 3 -> undo();
            case 4 -> redo();
            case 5 -> viewPlaylist();
            case 6 -> System.out.println("Exiting... Goodbye!");
            default -> System.out.println("Invalid choice. Try again!");
            }
          } while (choice != 6);
          
          
          sc.close();
       }
        
        private static ArrayList<String> clonePlaylist() {
            return new ArrayList<>(playlist);
}
        private static void addSong(Scanner sc) {
            System.out.print("Enter song name: ");
            String song = sc.nextLine();
            undoStack.push(clonePlaylist()); // save current state
            redoStack.clear(); // clear redo history
            playlist.add(song);
            System.out.println("Song \"" + song + "\" added to playlist.");
        }

        private static void removeSong() {
            if (playlist.isEmpty()) {
                System.out.println("Playlist is empty. Nothing to remove.");
                return;
            }
            undoStack.push(clonePlaylist()); // save current state
            redoStack.clear(); // clear redo history
            String removed = playlist.remove(playlist.size() - 1);
            System.out.println("Song \"" + removed + "\" removed from playlist.");
        }

        private static void undo() {
            if (undoStack.isEmpty()) {
                System.out.println("Nothing to undo.");
                return;
            }
            redoStack.push(clonePlaylist()); // save current state for redo
            playlist = undoStack.pop(); // restore previous state
            System.out.println("Undo performed.");
        }

        private static void redo() {
            if (redoStack.isEmpty()) {
                System.out.println("Nothing to redo.");
                return;
            }
            undoStack.push(clonePlaylist()); // save current state for undo
            playlist = redoStack.pop(); // restore redo state
            System.out.println("Redo performed.");
        }

        private static void viewPlaylist() {
            if (playlist.isEmpty()) {
                System.out.println("Playlist is empty.");
            } else {
                System.out.println("\n--- Current Playlist ---");
                for (int i = 0; i < playlist.size(); i++) {
                    System.out.println((i + 1) + ". " + playlist.get(i));
                }
            }
        }
    }
