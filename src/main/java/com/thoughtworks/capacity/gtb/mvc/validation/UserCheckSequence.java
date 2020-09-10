package com.thoughtworks.capacity.gtb.mvc.validation;

import javax.validation.GroupSequence;

@GroupSequence({UserNameNotBlankCheck.class, UserNamePatternCheck.class, PassWordNotBlankCheck.class, PassWordSizeCheck.class, EmailCheck.class})
public interface UserCheckSequence {
}
