package com.pltone.distmsg.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 站点信息
 *
 * @author chenlong
 * @version 1.0 2018-11-22
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class Station {
    private String no;
    private String name;
}
