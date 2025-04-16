package ru.itmo.Core.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * Class which transfer result from methods
 */
@AllArgsConstructor
@Data
public class OperationResult {
    @NonNull
    private OperationResultVerdict verdict;
    @NonNull
    private String message;
}
