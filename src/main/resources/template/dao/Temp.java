package com.zglu.generator.target.{packageName}.dao;

import lombok.Data;

import javax.persistence.*;
{importString}
/**
 * @author {author}
 */
@Data
@Entity
@Table(name = "{tableName}")
public class {className} {
{fieldString}
}
