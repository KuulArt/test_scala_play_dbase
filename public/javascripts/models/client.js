/**
 * Created by kuulart on 16.15.3.
 */
/*global define*/
define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    'use strict';

    var Client = Backbone.Model.extend({
        urlRoot : '/client'
    });

    return Client;
});