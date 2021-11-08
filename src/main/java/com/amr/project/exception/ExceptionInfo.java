package com.amr.project.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Veilas on 11/8/2021.
 * Class: ExceptionInfo.java
 */

@Component
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
public class ExceptionInfo {
    private String info;
}
