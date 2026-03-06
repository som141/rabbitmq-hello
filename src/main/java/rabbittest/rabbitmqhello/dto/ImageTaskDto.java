package rabbittest.rabbitmqhello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageTaskDto {
    private String taskId;
    private String originalFileName;
    private int targetWidth;
    private int targetHeight;
}
