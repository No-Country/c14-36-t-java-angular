package com.nocountry.cashier.domain.service.email.template;

import com.nocountry.cashier.domain.usecase.email.TemplateStrategy;
import com.nocountry.cashier.enums.EnumsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.service.email
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
@Component
public class TemplateEmailConfirmation extends TemplateStrategy {
    @Override
    public String header() {
        return """
                <!DOCTYPE html>
                <html ⚡4email data-css-strict>
                  <head>
                    <meta charset="utf-8" />
                    <style amp4email-boilerplate>
                      body {
                        visibility: hidden;
                      }
                    </style>
                    <script async src="https://cdn.ampproject.org/v0.js"></script>
                    <style amp-custom>
                      .es-desk-hidden {
                        display: none;
                        float: left;
                        overflow: hidden;
                        width: 0;
                        max-height: 0;
                        line-height: 0;
                      }
                                
                      body {
                        width: 100%;
                        font-family: arial, "helvetica neue", helvetica, sans-serif;
                      }
                                
                      table {
                        border-collapse: collapse;
                        border-spacing: 0px;
                      }
                                
                      table td,
                      body,
                      .es-wrapper {
                        padding: 0;
                        margin: 0;
                      }
                                
                      .es-content,
                      .es-header,
                      .es-footer {
                        table-layout: fixed;
                        width: 100%;
                      }
                                
                      p,
                      hr {
                        margin: 0;
                      }
                                
                      h1,
                      h2,
                      h3,
                      h4,
                      h5 {
                        margin: 0;
                        line-height: 120%;
                        font-family: arial, "helvetica neue", helvetica, sans-serif;
                      }
                                
                      .es-left {
                        float: left;
                      }
                                
                      .es-right {
                        float: right;
                      }
                                
                      .es-p5 {
                        padding: 5px;
                      }
                                
                      .es-p5t {
                        padding-top: 5px;
                      }
                                
                      .es-p5b {
                        padding-bottom: 5px;
                      }
                                
                      .es-p5l {
                        padding-left: 5px;
                      }
                                
                      .es-p5r {
                        padding-right: 5px;
                      }
                                
                      .es-p10 {
                        padding: 10px;
                      }
                                
                      .es-p10t {
                        padding-top: 10px;
                      }
                                
                      .es-p10b {
                        padding-bottom: 10px;
                      }
                                
                      .es-p10l {
                        padding-left: 10px;
                      }
                                
                      .es-p10r {
                        padding-right: 10px;
                      }
                                
                      .es-p15 {
                        padding: 15px;
                      }
                                
                      .es-p15t {
                        padding-top: 15px;
                      }
                                
                      .es-p15b {
                        padding-bottom: 15px;
                      }
                                
                      .es-p15l {
                        padding-left: 15px;
                      }
                                
                      .es-p15r {
                        padding-right: 15px;
                      }
                                
                      .es-p20 {
                        padding: 20px;
                      }
                                
                      .es-p20t {
                        padding-top: 20px;
                      }
                                
                      .es-p20b {
                        padding-bottom: 20px;
                      }
                                
                      .es-p20l {
                        padding-left: 20px;
                      }
                                
                      .es-p20r {
                        padding-right: 20px;
                      }
                                
                      .es-p25 {
                        padding: 25px;
                      }
                                
                      .es-p25t {
                        padding-top: 25px;
                      }
                                
                      .es-p25b {
                        padding-bottom: 25px;
                      }
                                
                      .es-p25l {
                        padding-left: 25px;
                      }
                                
                      .es-p25r {
                        padding-right: 25px;
                      }
                                
                      .es-p30 {
                        padding: 30px;
                      }
                                
                      .es-p30t {
                        padding-top: 30px;
                      }
                                
                      .es-p30b {
                        padding-bottom: 30px;
                      }
                                
                      .es-p30l {
                        padding-left: 30px;
                      }
                                
                      .es-p30r {
                        padding-right: 30px;
                      }
                                
                      .es-p35 {
                        padding: 35px;
                      }
                                
                      .es-p35t {
                        padding-top: 35px;
                      }
                                
                      .es-p35b {
                        padding-bottom: 35px;
                      }
                                
                      .es-p35l {
                        padding-left: 35px;
                      }
                                
                      .es-p35r {
                        padding-right: 35px;
                      }
                                
                      .es-p40 {
                        padding: 40px;
                      }
                                
                      .es-p40t {
                        padding-top: 40px;
                      }
                                
                      .es-p40b {
                        padding-bottom: 40px;
                      }
                                
                      .es-p40l {
                        padding-left: 40px;
                      }
                                
                      .es-p40r {
                        padding-right: 40px;
                      }
                                
                      .es-menu td {
                        border: 0;
                      }
                                
                      s {
                        text-decoration: line-through;
                      }
                                
                      p,
                      ul li,
                      ol li {
                        font-family: arial, "helvetica neue", helvetica, sans-serif;
                        line-height: 150%;
                      }
                                
                      ul li,
                      ol li {
                        margin-bottom: 15px;
                        margin-left: 0;
                      }
                                
                      a {
                        text-decoration: underline;
                      }
                                
                      .es-menu td a {
                        text-decoration: none;
                        display: block;
                        font-family: arial, "helvetica neue", helvetica, sans-serif;
                      }
                                
                      .es-wrapper {
                        width: 100%;
                        height: 100%;
                      }
                                
                      .es-wrapper-color,
                      .es-wrapper {
                        background-color: #fafafa;
                      }
                                
                      .es-header {
                        background-color: transparent;
                      }
                                
                      .es-header-body {
                        background-color: transparent;
                      }
                                
                      .es-header-body p,
                      .es-header-body ul li,
                      .es-header-body ol li {
                        color: #333333;
                        font-size: 14px;
                      }
                                
                      .es-header-body a {
                        color: #666666;
                        font-size: 14px;
                      }
                                
                      .es-content-body {
                        background-color: #ffffff;
                      }
                                
                      .es-content-body p,
                      .es-content-body ul li,
                      .es-content-body ol li {
                        color: #333333;
                        font-size: 14px;
                      }
                                
                      .es-content-body a {
                        color: #5c68e2;
                        font-size: 14px;
                      }
                                
                      .es-footer {
                        background-color: transparent;
                      }
                                
                      .es-footer-body {
                        background-color: #ffffff;
                      }
                                
                      .es-footer-body p,
                      .es-footer-body ul li,
                      .es-footer-body ol li {
                        color: #333333;
                        font-size: 12px;
                      }
                                
                      .es-footer-body a {
                        color: #333333;
                        font-size: 12px;
                      }
                                
                      .es-infoblock,
                      .es-infoblock p,
                      .es-infoblock ul li,
                      .es-infoblock ol li {
                        line-height: 120%;
                        font-size: 12px;
                        color: #cccccc;
                      }
                                
                      .es-infoblock a {
                        font-size: 12px;
                        color: #cccccc;
                      }
                                
                      h1 {
                        font-size: 46px;
                        font-style: normal;
                        font-weight: bold;
                        color: #333333;
                      }
                                
                      h2 {
                        font-size: 26px;
                        font-style: normal;
                        font-weight: bold;
                        color: #333333;
                      }
                                
                      h3 {
                        font-size: 20px;
                        font-style: normal;
                        font-weight: bold;
                        color: #333333;
                      }
                                
                      .es-header-body h1 a,
                      .es-content-body h1 a,
                      .es-footer-body h1 a {
                        font-size: 46px;
                      }
                                
                      .es-header-body h2 a,
                      .es-content-body h2 a,
                      .es-footer-body h2 a {
                        font-size: 26px;
                      }
                                
                      .es-header-body h3 a,
                      .es-content-body h3 a,
                      .es-footer-body h3 a {
                        font-size: 20px;
                      }
                                
                      a.es-button,
                      button.es-button {
                        padding: 10px 30px 10px 30px;
                        display: inline-block;
                        background: #5c68e2;
                        border-radius: 0px;
                        font-size: 20px;
                        font-family: arial, "helvetica neue", helvetica, sans-serif;
                        font-weight: normal;
                        font-style: normal;
                        line-height: 120%;
                        color: #ffffff;
                        text-decoration: none;
                        width: auto;
                        text-align: center;
                      }
                                
                      .es-button-border {
                        border-style: solid solid solid solid;
                        border-color: #2cb543 #2cb543 #2cb543 #2cb543;
                        background: #5c68e2;
                        border-width: 0px 0px 0px 0px;
                        display: inline-block;
                        border-radius: 0px;
                        width: auto;
                      }
                                
                      .es-menu amp-img,
                      .es-button amp-img {
                        vertical-align: middle;
                      }
                                
                      @media only screen and (max-width: 600px) {
                        p,
                        ul li,
                        ol li,
                        a {
                          line-height: 150%;
                        }
                                
                        h1,
                        h2,
                        h3,
                        h1 a,
                        h2 a,
                        h3 a {
                          line-height: 120%;
                        }
                                
                        h1 {
                          font-size: 36px;
                          text-align: left;
                        }
                                
                        h2 {
                          font-size: 26px;
                          text-align: left;
                        }
                                
                        h3 {
                          font-size: 20px;
                          text-align: left;
                        }
                                
                        .es-header-body h1 a,
                        .es-content-body h1 a,
                        .es-footer-body h1 a {
                          font-size: 36px;
                          text-align: left;
                        }
                                
                        .es-header-body h2 a,
                        .es-content-body h2 a,
                        .es-footer-body h2 a {
                          font-size: 26px;
                          text-align: left;
                        }
                                
                        .es-header-body h3 a,
                        .es-content-body h3 a,
                        .es-footer-body h3 a {
                          font-size: 20px;
                          text-align: left;
                        }
                                
                        .es-menu td a {
                          font-size: 12px;
                        }
                                
                        .es-header-body p,
                        .es-header-body ul li,
                        .es-header-body ol li,
                        .es-header-body a {
                          font-size: 14px;
                        }
                                
                        .es-content-body p,
                        .es-content-body ul li,
                        .es-content-body ol li,
                        .es-content-body a {
                          font-size: 16px;
                        }
                                
                        .es-footer-body p,
                        .es-footer-body ul li,
                        .es-footer-body ol li,
                        .es-footer-body a {
                          font-size: 14px;
                        }
                                
                        .es-infoblock p,
                        .es-infoblock ul li,
                        .es-infoblock ol li,
                        .es-infoblock a {
                          font-size: 12px;
                        }
                                
                        *[class="gmail-fix"] {
                          display: none;
                        }
                                
                        .es-m-txt-c,
                        .es-m-txt-c h1,
                        .es-m-txt-c h2,
                        .es-m-txt-c h3 {
                          text-align: center;
                        }
                                
                        .es-m-txt-r,
                        .es-m-txt-r h1,
                        .es-m-txt-r h2,
                        .es-m-txt-r h3 {
                          text-align: right;
                        }
                                
                        .es-m-txt-l,
                        .es-m-txt-l h1,
                        .es-m-txt-l h2,
                        .es-m-txt-l h3 {
                          text-align: left;
                        }
                                
                        .es-m-txt-r amp-img {
                          float: right;
                        }
                                
                        .es-m-txt-c amp-img {
                          margin: 0 auto;
                        }
                                
                        .es-m-txt-l amp-img {
                          float: left;
                        }
                                
                        .es-button-border {
                          display: inline-block;
                        }
                                
                        a.es-button,
                        button.es-button {
                          font-size: 20px;
                          display: inline-block;
                        }
                                
                        .es-adaptive table,
                        .es-left,
                        .es-right {
                          width: 100%;
                        }
                                
                        .es-content table,
                        .es-header table,
                        .es-footer table,
                        .es-content,
                        .es-footer,
                        .es-header {
                          width: 100%;
                          max-width: 600px;
                        }
                                
                        .es-adapt-td {
                          display: block;
                          width: 100%;
                        }
                                
                        .adapt-img {
                          width: 100%;
                          height: auto;
                        }
                                
                        td.es-m-p0 {
                          padding: 0;
                        }
                                
                        td.es-m-p0r {
                          padding-right: 0;
                        }
                                
                        td.es-m-p0l {
                          padding-left: 0;
                        }
                                
                        td.es-m-p0t {
                          padding-top: 0;
                        }
                                
                        td.es-m-p0b {
                          padding-bottom: 0;
                        }
                                
                        td.es-m-p20b {
                          padding-bottom: 20px;
                        }
                                
                        .es-mobile-hidden,
                        .es-hidden {
                          display: none;
                        }
                                
                        tr.es-desk-hidden,
                        td.es-desk-hidden,
                        table.es-desk-hidden {
                          width: auto;
                          overflow: visible;
                          float: none;
                          max-height: inherit;
                          line-height: inherit;
                        }
                                
                        tr.es-desk-hidden {
                          display: table-row;
                        }
                                
                        table.es-desk-hidden {
                          display: table;
                        }
                                
                        td.es-desk-menu-hidden {
                          display: table-cell;
                        }
                                
                        .es-menu td {
                          width: 1%;
                        }
                                
                        table.es-table-not-adapt,
                        .esd-block-html table {
                          width: auto;
                        }
                                
                        table.es-social {
                          display: inline-block;
                        }
                                
                        table.es-social td {
                          display: inline-block;
                        }
                                
                        td.es-m-p5 {
                          padding: 5px;
                        }
                                
                        td.es-m-p5t {
                          padding-top: 5px;
                        }
                                
                        td.es-m-p5b {
                          padding-bottom: 5px;
                        }
                                
                        td.es-m-p5r {
                          padding-right: 5px;
                        }
                                
                        td.es-m-p5l {
                          padding-left: 5px;
                        }
                                
                        td.es-m-p10 {
                          padding: 10px;
                        }
                                
                        td.es-m-p10t {
                          padding-top: 10px;
                        }
                                
                        td.es-m-p10b {
                          padding-bottom: 10px;
                        }
                                
                        td.es-m-p10r {
                          padding-right: 10px;
                        }
                                
                        td.es-m-p10l {
                          padding-left: 10px;
                        }
                                
                        td.es-m-p15 {
                          padding: 15px;
                        }
                                
                        td.es-m-p15t {
                          padding-top: 15px;
                        }
                                
                        td.es-m-p15b {
                          padding-bottom: 15px;
                        }
                                
                        td.es-m-p15r {
                          padding-right: 15px;
                        }
                                
                        td.es-m-p15l {
                          padding-left: 15px;
                        }
                                
                        td.es-m-p20 {
                          padding: 20px;
                        }
                                
                        td.es-m-p20t {
                          padding-top: 20px;
                        }
                                
                        td.es-m-p20r {
                          padding-right: 20px;
                        }
                                
                        td.es-m-p20l {
                          padding-left: 20px;
                        }
                                
                        td.es-m-p25 {
                          padding: 25px;
                        }
                                
                        td.es-m-p25t {
                          padding-top: 25px;
                        }
                                
                        td.es-m-p25b {
                          padding-bottom: 25px;
                        }
                                
                        td.es-m-p25r {
                          padding-right: 25px;
                        }
                                
                        td.es-m-p25l {
                          padding-left: 25px;
                        }
                                
                        td.es-m-p30 {
                          padding: 30px;
                        }
                                
                        td.es-m-p30t {
                          padding-top: 30px;
                        }
                                
                        td.es-m-p30b {
                          padding-bottom: 30px;
                        }
                                
                        td.es-m-p30r {
                          padding-right: 30px;
                        }
                                
                        td.es-m-p30l {
                          padding-left: 30px;
                        }
                                
                        td.es-m-p35 {
                          padding: 35px;
                        }
                                
                        td.es-m-p35t {
                          padding-top: 35px;
                        }
                                
                        td.es-m-p35b {
                          padding-bottom: 35px;
                        }
                                
                        td.es-m-p35r {
                          padding-right: 35px;
                        }
                                
                        td.es-m-p35l {
                          padding-left: 35px;
                        }
                                
                        td.es-m-p40 {
                          padding: 40px;
                        }
                                
                        td.es-m-p40t {
                          padding-top: 40px;
                        }
                                
                        td.es-m-p40b {
                          padding-bottom: 40px;
                        }
                                
                        td.es-m-p40r {
                          padding-right: 40px;
                        }
                                
                        td.es-m-p40l {
                          padding-left: 40px;
                        }
                                
                        .es-desk-hidden {
                          display: table-row;
                          width: auto;
                          overflow: visible;
                          max-height: inherit;
                        }
                      }
                    </style>
                  </head>
                """;
    }

    @Override
    public String body() {
        return """
                <body>
                    <div dir="ltr" class="es-wrapper-color" lang="en">
                      <table class="es-wrapper" width="100%" cellspacing="0" cellpadding="0">
                        <tr>
                          <td valign="top">
                            <table cellpadding="0" cellspacing="0" class="es-header" align="center">
                              <tr>
                                <td align="center">
                                  <table
                                    bgcolor="#ffffff"
                                    class="es-header-body"
                                    align="center"
                                    cellpadding="0"
                                    cellspacing="0"
                                    width="600"
                                  >
                                    <tr>
                                      <td class="es-p10t es-p10b es-p20r es-p20l" align="left">
                                        <table cellpadding="0" cellspacing="0" width="100%">
                                          <tr>
                                            <td width="560" class="es-m-p0r" valign="top" align="center">
                                              <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                  <td align="center" style="display: none"></td>
                                                </tr>
                                              </table>
                                            </td>
                                          </tr>
                                        </table>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td class="es-p20b es-p10r es-p5l" align="left">
                                        <!--[if mso]><table width="585" cellpadding="0" cellspacing="0"><tr><td width="265" valign="top"><![endif]-->
                                        <table class="es-left" cellspacing="0" cellpadding="0" align="left">
                                          <tr>
                                            <td width="265" align="left">
                                              <table width="100%" cellspacing="0" cellpadding="0">
                                                <tr>
                                                  <td align="center" style="font-size: 0px">
                                                    <amp-img
                                                      src="https://fbfatde.stripocdn.email/content/guids/CABINET_0abb6030843bde3990783b0b392503d2d110b347f6d8d3184532bebee282013f/images/cashier_eIg.png"
                                                      alt
                                                      style="display: block"
                                                      width="265"
                                                      height="53"
                                                    ></amp-img>
                                                  </td>
                                                </tr>
                                              </table>
                                            </td>
                                          </tr>
                                        </table>
                                        <!--[if mso]></td><td width="15"></td><td width="305" valign="top"><![endif]-->
                                        <table class="es-right" cellspacing="0" cellpadding="0" align="right">
                                          <tr>
                                            <td width="305" align="left">
                                              <table width="100%" cellspacing="0" cellpadding="0">
                                                <tr>
                                                  <td class="es-p10t es-m-txt-c" align="right">
                                                    <span
                                                      class="msohide es-button-border"
                                                      style="border-radius: 10px; background: #13444d"
                                                      ><a
                                                        href="https://viewstripo.email/"
                                                        class="es-button"
                                                        target="_blank"
                                                        style="
                                                          font-weight: bold;
                                                          border-radius: 10px;
                                                          background: #13444d;
                                                          mso-border-alt: 10px solid #13444d;
                                                        "
                                                        >${value1}</a
                                                      ></span
                                                    >
                                                  </td>
                                                </tr>
                                              </table>
                                            </td>
                                          </tr>
                                        </table>
                                      </td>
                                    </tr>
                                  </table>
                                </td>
                              </tr>
                            </table>
                            <table cellpadding="0" cellspacing="0" class="es-content" align="center">
                              <tr>
                                <td align="center">
                                  <table
                                    bgcolor="#ffffff"
                                    class="es-content-body"
                                    align="center"
                                    cellpadding="0"
                                    cellspacing="0"
                                    width="600"
                                  >
                                    <tr>
                                      <td
                                        class="es-p30t es-p30b es-p20r es-p20l"
                                        align="left"
                                        bgcolor="#ffffff"
                                        style="background-color: #ffffff"
                                      >
                                        <table cellpadding="0" cellspacing="0" width="100%">
                                          <tr>
                                            <td width="560" align="center" valign="top">
                                              <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                  <td align="center" class="es-p10t es-p10b" style="font-size: 0px">
                                                    <amp-img
                                                      src="https://fbfatde.stripocdn.email/content/guids/CABINET_67e080d830d87c17802bd9b4fe1c0912/images/55191618237638326.png"
                                                      alt
                                                      style="display: block"
                                                      width="100"
                                                      class="adapt-img"
                                                      height="72"
                                                      layout="responsive"
                                                    ></amp-img>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td align="center" class="es-p10b es-m-txt-c">
                                                    <h1
                                                      style="
                                                        font-size: 46px;
                                                        line-height: 46px;
                                                        font-family: verdana, geneva, sans-serif;
                                                      "
                                                    >
                                                      Confirm Your Email
                                                    </h1>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td align="center" class="es-p5t es-p5b es-p40r es-p40l es-m-p0r es-m-p0l">
                                                    <p>
                                                      You’ve received this message because your email address has been registered with
                                                      our site. Please click the button below to verify your email address and confirm
                                                      that you are the owner of this account.
                                                    </p>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td align="center" class="es-p10t es-p5b">
                                                    <p>If you did not register with us, please disregard this email.</p>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td align="center" class="es-p10t es-p10b">
                                                    <span
                                                      class="msohide es-button-border"
                                                      style="border-radius: 7px; background: #005c81"
                                                      ><a
                                                        href="${value2}"
                                                        class="es-button"
                                                        target="_blank"
                                                        style="
                                                          padding-left: 30px;
                                                          padding-right: 30px;
                                                          border-radius: 7px;
                                                          font-weight: bold;
                                                          background: #005c81;
                                                          font-family: verdana, geneva, sans-serif;
                                                        "
                                                        >CONFIRM YOUR EMAIL</a
                                                      ></span
                                                    >
                                           
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td align="center" class="es-p5t es-p5b es-p40r es-p40l es-m-p0r es-m-p0l">
                                                    <p>Once confirmed, this email will be uniquely associated with your account.</p>
                                                  </td>
                                                </tr>
                                              </table>
                                            </td>
                                          </tr>
                                        </table>
                                      </td>
                                    </tr>
                                  </table>
                                </td>
                              </tr>
                            </table>
                """;
    }

    @Override
    public String footer() {

        return """
                <table cellpadding="0" cellspacing="0" class="es-footer" align="center">
                              <tr>
                                <td align="center">
                                  <table
                                    class="es-footer-body"
                                    align="center"
                                    cellpadding="0"
                                    cellspacing="0"
                                    width="640"
                                    style="background-color: transparent"
                                  >
                                    <tr>
                                      <td class="es-p20t es-p20b es-p20r es-p20l" align="left">
                                        <table cellpadding="0" cellspacing="0" width="100%">
                                          <tr>
                                            <td width="600" align="left">
                                              <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                  <td align="center" class="es-p5t es-p15b" style="font-size: 0px">
                                                    <table cellpadding="0" cellspacing="0" class="es-table-not-adapt es-social">
                                                      <tr>
                                                        <td align="center" valign="top" class="es-p40r">
                                                          <amp-img
                                                            title="Facebook"
                                                            src="https://fbfatde.stripocdn.email/content/assets/img/social-icons/logo-black/facebook-logo-black.png"
                                                            alt="Fb"
                                                            width="32"
                                                            height="32"
                                                          ></amp-img>
                                                        </td>
                                                        <td align="center" valign="top" class="es-p40r">
                                                          <amp-img
                                                            title="Twitter"
                                                            src="https://fbfatde.stripocdn.email/content/assets/img/social-icons/logo-black/twitter-logo-black.png"
                                                            alt="Tw"
                                                            width="32"
                                                            height="32"
                                                          ></amp-img>
                                                        </td>
                                                        <td align="center" valign="top" class="es-p40r">
                                                          <amp-img
                                                            title="Instagram"
                                                            src="https://fbfatde.stripocdn.email/content/assets/img/social-icons/logo-black/instagram-logo-black.png"
                                                            alt="Inst"
                                                            width="32"
                                                            height="32"
                                                          ></amp-img>
                                                        </td>
                                                        <td align="center" valign="top">
                                                          <amp-img
                                                            title="Youtube"
                                                            src="https://fbfatde.stripocdn.email/content/assets/img/social-icons/logo-black/youtube-logo-black.png"
                                                            alt="Yt"
                                                            width="32"
                                                            height="32"
                                                          ></amp-img>
                                                        </td>
                                                      </tr>
                                                    </table>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td align="center" class="es-p35b">
                                                    <p
                                                      style="
                                                        font-size: 14px;
                                                        font-family: arial, 'helvetica neue', helvetica, sans-serif;
                                                      "
                                                    >
                                                      <strong>Cashier © 2023 No Country, Inc. All Rights Reserved.</strong>
                                                    </p>
                                                    <p
                                                      style="
                                                        font-size: 14px;
                                                        font-family: arial, 'helvetica neue', helvetica, sans-serif;
                                                      "
                                                    >
                                                      <strong
                                                        >C14-36 DEVS Raul; Peche; Exequiel; Antonio; Machicado and Jorge</strong
                                                      >
                                                    </p>
                                                  </td>
                                                </tr>
                                              </table>
                                            </td>
                                          </tr>
                                        </table>
                                      </td>
                                    </tr>
                                  </table>
                                </td>
                              </tr>
                            </table>
                          </td>
                        </tr>
                      </table>
                    </div>
                  </body>
                </html>""";
    }

    @Override
    public EnumsTemplate getTemplateEmail() {
        return EnumsTemplate.CONFIRM_EMAIL;
    }

    @Override
    public String formatEmailTemplate(Object... values) {
        String template = buildTemplate();
        StringBuilder sb = new StringBuilder(template);

        for (int i = 0; i < values.length; i++) {
            String placeholder= "${value"+(i+1)+"}";
            String value=(values[i] != null)? String.valueOf(values[i].toString()): "";
            int startIndex =sb.indexOf(placeholder);
            int endIndex= startIndex+placeholder.length();
            sb.replace(startIndex,endIndex,value);
        }
        return sb.toString();
    }

}
