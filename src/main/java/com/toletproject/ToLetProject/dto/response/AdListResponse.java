package com.toletproject.ToLetProject.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdListResponse {
    boolean found;
    int pageNo;
    int size;
    long totalSize;
    List<AdResponse> adRespons;
}
