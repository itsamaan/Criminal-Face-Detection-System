public interface FaceRecognitionService {
    boolean detectFace(Image image);
    List<Criminal> compareFaces(Image image, List<Criminal> criminals);
    // Other face recognition methods
}
